package io.akryl.todomvc.components

import io.akryl.Component
import io.akryl.css.Color
import io.akryl.css.classMap
import io.akryl.css.css
import io.akryl.css.px
import io.akryl.html.A
import io.akryl.html.Li
import io.akryl.html.Span
import io.akryl.html.Ul
import io.akryl.react.ReactNode
import io.akryl.todomvc.store.TodoStore

private val filters by css {
  margin(0.px)
  padding(0.px)
  listStyleType.none()
  position.absolute()
  right(0.px)
  left(0.px)
}

private val filterLink by css {
  color.inherit()
  margin(3.px)
  padding(3.px, 7.px)
  textDecoration.none()
  border.solid(1.px, Color.transparent)
  borderRadius(3.px)

  hover {
    borderColor.rgba(175, 47, 47, 0.1)
  }
}

private val selected by css {
  filterLink {
    borderColor.rgba(175, 47, 47, 0.2)
  }
}

private val filterView by css {
  display.inline()
}

class FiltersView : Component() {
  override fun render(): ReactNode {
    val store = TodoStore.use()

    val children = ArrayList<ReactNode>()
    for ((index, filter) in store.filters.withIndex()) {
      children.add(
        Li(
          classes = classMap(
            filterView to true,
            selected to (filter===store.filter)
          ),
          child = A(clazz = filterLink, href = filter.url, text = filter.name),
          onClick = {
            store.filter = filter
            it.preventDefault()
          }
        )
      )
      if (index < store.filters.size - 1) {
        children.add(Span())
      }
    }

    return Ul(clazz = filters, children = children)
  }
}
