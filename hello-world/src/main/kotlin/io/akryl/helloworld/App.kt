package io.akryl.helloworld

import io.akryl.*
import io.akryl.html.Div
import io.akryl.html.H1
import kotlin.browser.document

class HelloWorld : StatelessWidget() {
  override fun style() = css {
    "h1" {
      textAlign = "center"
    }
  }

  override fun build(context: BuildContext) = Div(
    H1(text = "Hello, World!")
  )
}

fun main() {
  val app = document.getElementById("app")!!
  hotMount(app, HelloWorld())
}
