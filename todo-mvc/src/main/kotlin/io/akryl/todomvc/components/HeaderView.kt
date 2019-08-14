package io.akryl.todomvc.components

import io.akryl.Component
import io.akryl.css.css
import io.akryl.css.pct
import io.akryl.css.px
import io.akryl.css.rgba
import io.akryl.html.H1
import io.akryl.html.Header
import io.akryl.html.Input
import io.akryl.react.ReactNode
import io.akryl.todomvc.Theme
import io.akryl.todomvc.store.TodoStore
import io.akryl.useState
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.KeyboardEvent

private val header by css {
  position.absolute()
  top(-155.px)
  width(100.pct)
  fontSize(100.px)
  fontWeight.w100()
  textAlign.center()
  color.rgba(175, 47, 47, 0.15)
}

private val newTodo by css {
  position.relative()
  margin.none()
  width(100.pct)
  fontSize(24.px)
  fontFamily.inherit()
  fontWeight.inherit()
  lineHeight(1.4)
  color.inherit()
  boxSizing.borderBox()

  padding(16.px, 16.px, 16.px, 60.px)
  borderStyle.none()
  backgroundColor.rgba(0, 0, 0, 0.003)
  boxShadow(0.px, -2.px, 1.px, 0.px, rgba(0, 0, 0, 0.03))

  placeholder {
    fontStyle.italic()
    fontWeight.w300()
    color(Theme.background)
  }
}

class HeaderView : Component() {
  override fun render(): ReactNode {
    val store = TodoStore.use()
    var title by useState("")

    fun keyDown(event: KeyboardEvent) {
      if (event.keyCode == 13) {
        store.add(title)
        title = ""
      }
    }

    return Header(
      H1(clazz = header, text = "todos"),
      Input(
        clazz = newTodo,
        placeholder = "What's need to be done?",
        value = title,
        onChange = { title = (it.target as HTMLInputElement).value },
        onKeyDown = ::keyDown
      )
    )
  }
}
