package io.akryl.counter

import io.akryl.*
import io.akryl.html.Button
import io.akryl.html.Div
import io.akryl.html.Text
import kotlin.browser.document

class Counter : StatefulWidget() {
  override fun style() = css {
    ".root" {
      width = 200.px
      margin = "auto"
      display = "flex"
      flexDirection = "column"
      alignItems = "center"
    }

    ".title" {
      textAlign = "center"
      fontSize = 2.0.em
    }

    ".body" {
      display = "flex"
      alignItems = "center"
    }

    "button" {
      width = 32.px
      height = 32.px
    }

    ".value" {
      margin = "0 16px"
    }
  }

  override fun createState(context: BuildContext): State<*> = CounterState(context)
}

private class CounterState(context: BuildContext) : State<Counter>(context) {
  private var count = 0

  override fun build(context: BuildContext) = Div(clazz = "root", children = listOf(
    Div(clazz = "title", child = Text("Counter")),
    Div(clazz = "body", children = listOf(
      Button(child = Text("-"), onClick = { count -= 1 }),
      Div(clazz = "value", child = Text(count.toString())),
      Button(child = Text("+"), onClick = { count += 1 })
    ))
  ))
}


fun main() {
  val app = document.getElementById("app")!!
  hotMount(app, Counter())
}
