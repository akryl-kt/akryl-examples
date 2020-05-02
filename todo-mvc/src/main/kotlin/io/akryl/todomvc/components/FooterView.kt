package io.akryl.todomvc.components

import io.akryl.component
import io.akryl.dom.css.css
import io.akryl.dom.css.invoke
import io.akryl.dom.css.properties.*
import io.akryl.dom.html.footer

val footerClass by css(
    color(0x707070),
    padding(10.px, 15.px),
    height(20.px),
    textAlign.center(),
    borderTop.solid(1.px, Color(0xE6E6E6)),

    before(
        content(""),
        position.absolute(),
        right(0.px),
        bottom(0.px),
        left(0.px),
        height(50.px),
        overflow.hidden(),

        boxShadow
            .add(0.px, 1.px, 1.px, 0.px, rgba(0, 0, 0, 0.2))
            .add(0.px, 8.px, 0.px, -3.px, Color(0xF6F6F6))
            .add(0.px, 9.px, 1.px, -3.px, rgba(0, 0, 0, 0.2))
            .add(0.px, 16.px, 0.px, -6.px, Color(0xF6F6F6))
            .add(0.px, 17.px, 2.px, -6.px, rgba(0, 0, 0, 0.2))
    )
)

fun footerView() = component {
    footer(className = footerClass, children = listOf(
        todoCountView(),
        filtersView(),
        clearCompletedView()
    ))
}
