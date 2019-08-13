package io.akryl.counter

import io.akryl.Component
import io.akryl.css.css
import io.akryl.css.em
import io.akryl.css.px
import io.akryl.html.Button
import io.akryl.html.Div
import io.akryl.html.Text
import io.akryl.react.ReactNode
import io.akryl.render
import io.akryl.useReactive
import kotlin.browser.document

private val root by css {
  width(200.px)
  margin.auto()
  display.flex()
  flexDirection.column()
  alignItems.center()
}

private val title by css {
  textAlign.center()
  fontSize(2.em)
}

private val body by css {
  display.flex()
  alignItems.center()
}

private val button by css {
  width(32.px)
  height(32.px)
}

private val value by css {
  margin(0.px, 16.px)
}

class Counter : Component() {
  override fun render(): ReactNode {
    var count by useReactive { 0 }

    return  Div(clazz = root, children = listOf(
      Div(clazz = title, child = Text("Counter")),
      Div(clazz = body, children = listOf(
        Button(clazz = button, child = Text("-"), onClick = { count -= 1 }),
        Div(clazz = value, child = Text(count.toString())),
        Button(clazz = button, child = Text("+"), onClick = { count += 1 })
      ))
    ))
  }
}

fun main() {
  val app = document.getElementById("app")!!
  render(Counter(), app)
}
