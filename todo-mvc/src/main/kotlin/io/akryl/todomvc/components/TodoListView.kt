package io.akryl.todomvc.components

import io.akryl.component
import io.akryl.dom.css.css
import io.akryl.dom.css.properties.listStyleType
import io.akryl.dom.css.properties.margin
import io.akryl.dom.css.properties.padding
import io.akryl.dom.css.properties.px
import io.akryl.dom.html.ul
import io.akryl.todomvc.store.useStore
import io.akryl.withKey

private val todoList by css(
  margin(0.px),
  padding(0.px),
  listStyleType.none()
)

fun todoListView() = component {
    val store = useStore()

    ul(
        className = todoList,
        children = store.filtered.map { todoView(it.id).withKey(it.id) }
    )
}
