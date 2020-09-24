package com.nickerman.test3.base.presenter

import ru.terrakok.cicerone.Router
import javax.inject.Inject

 abstract class AbstractPresenter {
    @Inject
    protected lateinit var router: Router
}