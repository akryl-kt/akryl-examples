package io.akryl.todomvc

import io.akryl.todomvc.components.appView
import react_dom.ReactDom
import kotlin.browser.document

fun main() {
  ReactDom.render(appView(), document.getElementById("app"))
}
