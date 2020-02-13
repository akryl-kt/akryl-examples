package io.akryl.helloworld

import io.akryl.component
import io.akryl.dom.html.H1
import react_dom.ReactDom
import kotlin.browser.document

fun helloWorld() = component {
  H1(text = "Hello, World!")
}

fun main() {
  ReactDom.render(helloWorld(), document.getElementById("app"))
}
