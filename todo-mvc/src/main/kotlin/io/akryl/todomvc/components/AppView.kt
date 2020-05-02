package io.akryl.todomvc.components

import io.akryl.component
import io.akryl.dom.css.css
import io.akryl.dom.css.properties.*
import io.akryl.dom.html.div
import io.akryl.dom.html.section
import io.akryl.provider
import io.akryl.todomvc.store.TodoContext
import io.akryl.todomvc.store.TodoStore
import io.akryl.todomvc.store.initModel
import io.akryl.useState

private val app by css(
    width(550.px),
    backgroundColor.white(),
    margin(130.px, Linear.auto, 40.px, Linear.auto),
    position.relative(),
    boxShadow(0.px, 2.px, 4.px, 0.px, rgba(0, 0, 0, 0.2))
)

fun appView() = component {
    val (model, setModel) = useState { initModel() }
    val store = TodoStore(model, setModel)

    TodoContext.provider(value = store, children = listOf(
        section(className = app, children = listOf(
            div(
                headerView(),
                mainView(),
                footerView()
            )
        ))
    ))
}
