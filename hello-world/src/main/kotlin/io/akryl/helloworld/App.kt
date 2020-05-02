package io.akryl.helloworld

import io.akryl.component
import io.akryl.dom.html.h1
import react_dom.ReactDom
import kotlin.browser.document

fun helloWorld() = component {
  h1(text = "Hello, World!")
}

fun main() {
  ReactDom.render(helloWorld(), document.getElementById("app"))
}
