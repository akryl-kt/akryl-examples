package io.akryl.todomvc.components

import io.akryl.Component
import io.akryl.css.*
import io.akryl.html.*
import io.akryl.react.ReactNode
import io.akryl.todomvc.Theme
import io.akryl.todomvc.store.TodoStore
import io.akryl.useState
import io.akryl.utils.Else
import io.akryl.utils.If
import org.w3c.dom.HTMLInputElement

private val root by css {
  position.relative()
  fontSize(24.px)
  borderBottom.solid(1.px, Theme.background)
  listStyleType.none()

  hover {
    destroy {
      display.block()
    }
  }
}

private val toggle by css {
  height(40.px)
  background.none()
  opacity(0)
  textAlign.center()
  width(40.px)
  position.absolute()
  top(0.px)
  bottom(0.px)
  margin(Linear.auto, 0.px)
  borderStyle.none()

  put("-webkit-appearance", "none")
  put("appearance", "none")
}

private val label by css {
  backgroundImage.url("data:image/svg+xml;utf8,%3Csvg%20xmlns%3D%22http%3A//www.w3.org/2000/svg%22%20width%3D%2240%22%20height%3D%2240%22%20viewBox%3D%22-10%20-18%20100%20135%22%3E%3Ccircle%20cx%3D%2250%22%20cy%3D%2250%22%20r%3D%2250%22%20fill%3D%22none%22%20stroke%3D%22%23ededed%22%20stroke-width%3D%223%22/%3E%3C/svg%3E")
  backgroundRepeat.noRepeat()
  backgroundPosition(Horizontal.left, Vertical.center)
  wordBreak.breakAll()
  padding(15.px, 15.px, 15.px, 60.px)
  display.block()
  lineHeight(1.2)
  transition(color, 0.4.s)
}

private val destroy by css {
  display.none()
  position.absolute()
  top(0.px)
  right(10.px)
  bottom(0.px)
  width(40.px)
  height(40.px)
  margin(Linear.auto, 0.px)
  fontSize(30.px)
  color(0xCC9A9A)
  marginBottom(11.px)
  transition(color, 0.2.s, Timing.easeOut)

  after {
    content("×")
  }

  hover {
    color(0xaf5b5e)
  }
}

private val edit by css {
  position.relative()
  width(506.px)
  margin(0.px, 0.px, 0.px, 43.px)
  fontSize(24.px)
  fontFamily.inherit()
  fontWeight.inherit()
  lineHeight(1.4.em)
  color.inherit()
  padding(12.px, 16.px)
  border.solid(1.px, Color(0x909090))
  boxShadow(true, 0.px, -1.px, 5.px, 0.px, rgba(0, 0, 0, 0.2))
  boxSizing.borderBox()
}

private val completed by css {
  label {
    backgroundImage.url("data:image/svg+xml;utf8,%3Csvg%20xmlns%3D%22http%3A//www.w3.org/2000/svg%22%20width%3D%2240%22%20height%3D%2240%22%20viewBox%3D%22-10%20-18%20100%20135%22%3E%3Ccircle%20cx%3D%2250%22%20cy%3D%2250%22%20r%3D%2250%22%20fill%3D%22none%22%20stroke%3D%22%23bddad5%22%20stroke-width%3D%223%22/%3E%3Cpath%20fill%3D%22%235dc2af%22%20d%3D%22M72%2025L42%2071%2027%2056l-4%204%2020%2020%2034-52z%22/%3E%3C/svg%3E")
    textDecoration.lineThrough()
    color(0xD9D9D9)
  }
}

data class TodoView(val id: String) : Component(key = id) {
  override fun render(): ReactNode {
    val store = TodoStore.use()
    var editing by useState(false)
    val todo = store.get(id) ?: return Div()

    val rootClass = classMap(
      root to true,
      completed to todo.completed
    )

    return Li(classes = rootClass, children = listOf(
      *If(editing) {
        Input(
          clazz = edit,
          autoFocus = true,
          value = todo.title,
          onChange = { store.rename(id, (it.target as HTMLInputElement).value) },
          onBlur = { editing = false }
        )
      } Else {
        Div(children = listOf(
          Input(
            clazz = toggle,
            type = "checkbox",
            checked = todo.completed,
            onChange = { store.toggle(id) }
          ),
          Label(
            clazz = label,
            text = todo.title,
            onDoubleClick = { editing = true }
          ),
          Button(
            clazz = destroy,
            onClick = { store.remove(id) }
          )
        ))
      }
    ))
  }
}