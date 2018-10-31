# Kotlin-RxJava-Retrofit
这段时间在写基于gank API客户端Demo的时候，尝试着封装了一把MVP，下面就来谈谈这整个过程中的一些感想。

------

#### MVP

说起MVP，大家都是熟悉的不能再熟悉的了，但是要封装一套好的MVP还是很有必要的，可以很快捷的开发项目。下面谈谈我对MVP的理解吧。

![mvp](https://github.com/Taonce/Kotlin-RxJava-Retrofit/blob/master/img/mvp.png)

View层：取数据和显示界面，问P层索要数据，在界面上显示数据。

Presenter层：把View需要的操作传递给Model层，然后Model层处理完复杂操作之后通过P层来把结果传递给View层。

Model层：P层让我干什么我就干什么，有什么用和怎么用不需要管，只管处理，然后把处理后的结果回调给P层。

了解了三者的基本关系之后，接着来看看具体如何封装属于自己的MVP结构。我这里是按照自己的需求，大家仅供参考！

**Demo整体结构如下：**

![demo](https://github.com/Taonce/Kotlin-RxJava-Retrofit/blob/master/img/demo.png)

整个项目按照上图结构设计。

下面介绍两个比较重要的地方：`IBaseView`和`BasePresenter`

**IBaseView:**

其中`IBaseView`里面写了两个通用方法：在请求网络的时候用到的通用方法，显示和隐藏进度条。

```kotlin
interface IBaseView{
    fun showLoading()

    fun hideLoading()

}
```

当我们在实现具体`I***View`时，只需继承它就行了，不必每次都把`showLoading`和`hideLoading`写一遍，如：

```kotlin
interface IMainView : IBaseView {
    fun showAndroidData(bean: AndroidBean)

    fun showErrorMsg(msg: String = "亲，网络出现问题了哦~")
}
```

这是demo中的一个，另外定义了两个方法，一个是展示获取的Android数据，还有一个就是获取数据失败的方法。显示和隐藏进度条就交给`IBaseView`来实现。

**BasePresenter:**

而`BasePresenter`里面实现了P层和V层的绑定和解绑，这里需要知道，在MVP结构中，P和V是双向绑定的，如果我们只绑定不解绑的话，在`activity`已经销毁的情况下，`Presenter`如果还在执行操作，那么就会造成`activity`无法被释放的问题，产生内存泄漏。所以这里一定要注意P和V的解绑，具体实现是在`BaseActivity`中统一处理。具体代码如下：

```kotlin
package com.taonce.kotlindemo.base

open class BasePresenter<V> {

    private var view: V? = null
	
    // 绑定View
    fun attachView(view: V) {
        this.view = view
    }

    // 解绑
    fun detachView() {
		this.view = null
    }

    fun isAttach(): Boolean {
        return view != null
    }

}
```

同`IBaseView`一样，在实现具体的`***Presenter`的时候，也只需要继承`BasePresenter`就ok了。只需要另外加上自己的逻辑，不考虑每次的V和P的解绑。如：

```kotlin
package com.taonce.kotlindemo.presenter

import com.taonce.kotlindemo.base.BasePresenter
import com.taonce.kotlindemo.bean.AndroidBean
import com.taonce.kotlindemo.model.MainModel
import com.taonce.kotlindemo.contract.IMainModel
import com.taonce.kotlindemo.contract.IMainView

class MainPresenter(mView: IMainView) : BasePresenter<IMainView>(), IMainModel.OnGetAndroidDataListener {

    private var mView: IMainView? = mView
    private var mModel: IMainModel? = null

    init {
        mModel = MainModel()
    }

    fun getAndroidData(category: String, page: Int) {
        this.mView?.showLoading()
        this.mModel?.getAndroidData(category, page, this)
    }

    override fun onGetAndroidDataFinished(bean: AndroidBean?) {
        this.mView?.hideLoading()
        if (bean != null) {
            mView?.showAndroidData(bean)
            if (bean.error == "true") {
                mView?.showErrorMsg()
            }
        } else {
            mView?.showErrorMsg()
        }
    }

}
```



------

#### BaseActivity

在`BaseActivity`除了日常的`getLayoutId()` `initView()` `initData()` `initEvent()`四个抽象方法之外，主要还是封装了通用的进度条的显示和隐藏，P层和V层的绑定和解绑。

![BaseAcitity设计思想](https://github.com/Taonce/Kotlin-RxJava-Retrofit/blob/master/img/MVP-BaseActivity.png)

1. 通过实现IBaseView接口，把里面的方法进行重写，调用`showLoadingView()`和`hideLoadingView()`方法。
2. 通过泛型来实现P层的引用，然后通过`abstract fun getPresenter(): T`来得到具体的Presenter，这样就可以在`onCreate()`方法里面对P和V进行绑定，在`onDestory()`方法里对P层引用的View进行释放。

具体代码如下：

```kotlin
package com.taonce.kotlindemo.base

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.taonce.kotlindemo.R
import com.taonce.kotlindemo.ui.inter.IBaseView

abstract class BaseActivity<V, T : BasePresenter<V>> : AppCompatActivity(), IBaseView {

    private var baseLoadingView: BaseLoadingView? = null
    private var basePresenter: T? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        basePresenter = getPresenter()
        basePresenter?.attachView(this as V)
        initLoadingView()
        initView()
        initData()
        initEvent()
    }

    abstract fun getLayoutId(): Int

    abstract fun initData()

    abstract fun initView()

    abstract fun initEvent()

    abstract fun getPresenter(): T


    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()

        // 销毁dialog
        if (null != baseLoadingView && baseLoadingView!!.isShowing) {
            baseLoadingView?.dismiss()
        }
        baseLoadingView = null

        // 在activity销毁时，解绑activity和presenter
        if (basePresenter != null) {
            basePresenter?.detachView()
            basePresenter = null
        }
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onPause() {
        super.onPause()
    }

    private fun initLoadingView() {
        baseLoadingView = BaseLoadingView(this, R.style.transparent_dialog)
    }

    private fun showLoadingView() {
        if (null != baseLoadingView) {
            baseLoadingView?.show()
        } else {
            initLoadingView()
            baseLoadingView?.show()
        }
    }

    private fun hideLoadingView() {
        if (null != baseLoadingView && baseLoadingView!!.isShowing) {
            baseLoadingView?.cancel()
        }
    }

    /**
     * 将显示dialog和取消dialog放在了基础类中
     */
    override fun showLoading() {
        showLoadingView()
    }

    override fun hideLoading() {
        hideLoadingView()
    }

}

```

------

#### 网络

网络部分主要采用的是**OkHttp3**+**Retrofit2**的方式，通过`OKHttp3`设置公共头参、Log输出、超时连接等，`Retrofit2`是用来进行网络请求，最终的线程转换是通过**RxJava**来实现的，由于网络这块还没深入去探究，暂时只简单封装了一下，后期会在此基础上完善。具体的代码会在文末贴上链接。



------

整个封装就介绍到这里啦，如果大家觉得有什么不足的地方，欢迎随时交流沟通，期待你们的批评。项目已上传GitHub，地址为：https://github.com/Taonce/Kotlin-RxJava-Retrofit

# 使用
直接将工程clone到本地,基本的框架已经搭建好,只需根据自己项目的特点添加功能就行

⊙开源不易，希望给个star或者fork奖励

⊙长期不定时更新,开源地址:https://github.com/Taonce
