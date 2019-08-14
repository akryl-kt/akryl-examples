package io.akryl.helloworld

import io.akryl.*
import io.akryl.css.css
import io.akryl.html.Div
import io.akryl.html.H1
import js.module
import kotlin.browser.document

val root by css {
  textAlign.center()
}

class HelloWorld : Component() {
  override fun render() = Div(
    clazz = root,
    child = H1(text = "Hello, World!")
  )
}

fun main() {
  module.hot?.accept()
  val app = document.getElementById("app")!!
  render(HelloWorld(), app)
}
