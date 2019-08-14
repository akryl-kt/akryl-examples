package io.akryl.todomvc

import io.akryl.css.*

fun styles() = globalCss {
  body {
    backgroundColor(0xF5F5F5)
    font(14.px, "Helvetica Neue", "Helvetica", "Arial", "sans-serif")
    lineHeight(1.4.em)
    color(0x4D4D4D)
  }

  input {
    outline.none()
  }

  button {
    margin.none()
    padding.none()
    border.none()
    background.none()
    fontSize(100.pct)
    verticalAlign.baseline()
    fontFamily.inherit()
    fontWeight.inherit()
    color.inherit()
    outline.none()

    put("appearance", "none")
    put("-webkit-appearance", "none")
  }
}