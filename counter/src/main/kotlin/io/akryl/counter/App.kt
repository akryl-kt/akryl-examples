package io.akryl.counter

import io.akryl.component
import io.akryl.dom.html.button
import io.akryl.dom.html.div
import io.akryl.dom.html.h2
import io.akryl.useState
import react_dom.ReactDom
import kotlin.browser.document

fun counter() = component {
    val (count, setCount) = useState(0)

    div(
        h2(text = "Count: $count"),
        button(onClick = { setCount(count - 1) }, text = "-"),
        button(onClick = { setCount(count + 1) }, text = "+")
    )
}

fun main() {
    ReactDom.render(counter(), document.getElementById("app"))
}
