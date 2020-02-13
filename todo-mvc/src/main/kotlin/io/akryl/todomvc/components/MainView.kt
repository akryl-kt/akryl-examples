package io.akryl.todomvc.components

import io.akryl.component
import io.akryl.dom.css.classMap
import io.akryl.dom.css.css
import io.akryl.dom.css.invoke
import io.akryl.dom.css.properties.*
import io.akryl.dom.html.Div
import io.akryl.dom.html.Input
import io.akryl.dom.html.Label
import io.akryl.todomvc.store.useStore

private val main by css(
    position.relative(),
    zIndex(2),
    borderTop.solid(1.px, Color(0xE6E6E6))
)

private val toggleAllInput by css(
    background.none(),
    textAlign.center(),
    borderStyle.none(),
    opacity(0),
    position.absolute()
)

private val toggleAllLabel by css(
    width(60.px),
    height(34.px),
    fontSize(0.px),
    position.absolute(),
    top(-52.px),
    left(-13.px),
    transform.rotate(90.deg),
    color(0xE6E6E6),

    before(
        content("❯"),
        fontSize(22.px),
        padding(10.px, 27.px, 10.px, 27.px)
    )
)

private val completed by css(
    color(0x737373)
)

fun mainView() = component {
    val store = useStore()

    val labelClass = classMap(
        toggleAllLabel to true,
        completed to store.allCompleted
    )

    Div(className = main, children = listOf(
        Input(
            id = "toggle-all",
            className = toggleAllInput,
            type = "checkbox",
            checked = store.allCompleted,
            onChange = { store.toggleAll() }
        ),
        Label(`for` = "toggle-all", className = labelClass),
        todoListView()
    ))
}
