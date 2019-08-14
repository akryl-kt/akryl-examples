package io.akryl.todomvc.components

import io.akryl.Component
import io.akryl.css.css
import io.akryl.css.px
import io.akryl.html.Button
import io.akryl.react.ReactNode
import io.akryl.todomvc.store.TodoStore

private val clearCompleted by css {
  float.right()
  position.relative()
  lineHeight(20.px)
  textDecoration.none()
  cursor.pointer()

  hover {
    textDecoration.underline()
  }
}

class ClearCompletedView : Component() {
  override fun render(): ReactNode {
    val store = TodoStore.use()

    return Button(
      clazz = clearCompleted,
      text = "Clear completed",
      onClick = { store.clearCompleted() }
    )
  }
}
