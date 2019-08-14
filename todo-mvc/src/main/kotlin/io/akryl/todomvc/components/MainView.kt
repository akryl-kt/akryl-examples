package io.akryl.todomvc.components

import io.akryl.Component
import io.akryl.css.classMap
import io.akryl.css.css
import io.akryl.css.deg
import io.akryl.css.px
import io.akryl.html.Div
import io.akryl.html.Input
import io.akryl.html.Label
import io.akryl.react.ReactNode
import io.akryl.todomvc.Theme
import io.akryl.todomvc.store.TodoStore

private val main by css {
  position.relative()
  zIndex(2)
  borderTop.solid(1.px, Theme.background)
}

private val toggleAllInput by css {
  background.none()
  textAlign.center()
  borderStyle.none()
  opacity(0)
  position.absolute()
}

private val toggleAllLabel by css {
  width(60.px)
  height(34.px)
  fontSize(0.px)
  position.absolute()
  top(-52.px)
  left(-13.px)
  transform.rotate(90.deg)
  color(Theme.background)

  before {
    content("❯")
    fontSize(22.px)
    padding(10.px, 27.px, 10.px, 27.px)
  }
}

private val completed by css {
  color(0x737373)
}

class MainView : Component() {
  override fun render(): ReactNode {
    val store = TodoStore.use()

    val labelClass = classMap(
      toggleAllLabel to true,
      completed to store.allCompleted
    )

    return Div(clazz = main, children = listOf(
      Input(
        id = "toggle-all",
        clazz = toggleAllInput,
        type = "checkbox",
        checked = store.allCompleted,
        onChange = { store.toggleAll() }
      ),
      Label(`for` = "toggle-all", classes = labelClass),
      TodoListView()
    ))
  }
}
