package io.akryl.todomvc.components

import io.akryl.Component
import io.akryl.css.css
import io.akryl.css.px
import io.akryl.html.Ul
import io.akryl.react.ReactNode
import io.akryl.todomvc.store.TodoStore

private val todoList by css {
  margin(0.px)
  padding(0.px)
  listStyleType.none()
}

class TodoListView : Component() {
  override fun render(): ReactNode {
    val store = TodoStore.use()

    return Ul(
      clazz = todoList,
      children = store.filtered.map { TodoView(it.id) }
    )
  }
}
