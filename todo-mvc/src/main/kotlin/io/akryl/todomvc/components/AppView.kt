package io.akryl.todomvc.components

import io.akryl.Component
import io.akryl.css.Linear
import io.akryl.css.css
import io.akryl.css.px
import io.akryl.css.rgba
import io.akryl.html.Div
import io.akryl.html.Section
import io.akryl.react.ReactNode
import io.akryl.todomvc.store.TodoContext
import io.akryl.todomvc.store.TodoStore
import io.akryl.useRef

private val app by css {
  width(550.px)
  backgroundColor.white()
  margin(130.px, Linear.auto, 40.px, Linear.auto)
  position.relative()
  boxShadow(0.px, 2.px, 4.px, 0.px, rgba(0, 0, 0, 0.2))
}

class AppView : Component() {
  override fun render(): ReactNode {
    val store by useRef { TodoStore() }

    return TodoContext.provide(
      store = store,
      child = Section(
        clazz = app,
        child = Div(
          HeaderView(),
          MainView(),
          FooterView()
        )
      )
    )
  }
}
