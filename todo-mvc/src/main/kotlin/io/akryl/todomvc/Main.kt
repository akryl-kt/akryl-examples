package io.akryl.todomvc

import io.akryl.render
import io.akryl.todomvc.components.AppView
import kotlin.browser.document

fun main() {
  val app = document.getElementById("app")!!
  styles()
  render(AppView(), app)
}
