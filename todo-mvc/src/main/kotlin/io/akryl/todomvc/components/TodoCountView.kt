package io.akryl.todomvc.components

import io.akryl.Component
import io.akryl.css.css
import io.akryl.html.Span
import io.akryl.react.ReactNode
import io.akryl.todomvc.store.TodoStore

private val todoCount by css {
  float.left()
  textAlign.left()
}

class TodoCountView : Component() {
  override fun render(): ReactNode {
    val store = TodoStore.use()
    val count = store.items.count { !it.completed }
    val str = if (count == 1) "1 item left" else "$count items left"

    return Span(clazz = todoCount, text = str)
  }
}
