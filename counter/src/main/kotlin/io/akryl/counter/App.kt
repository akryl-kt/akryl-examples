package io.akryl.counter

import io.akryl.component
import io.akryl.dom.html.Button
import io.akryl.dom.html.Div
import io.akryl.dom.html.H2
import io.akryl.dom.html.Text
import io.akryl.useState
import react_dom.ReactDom
import kotlin.browser.document

fun counter() = component {
    val (count, setCount) = useState(0)

    Div(
        H2(text = "Count: $count"),
        Button(onClick = { setCount(count - 1) }, text = "-"),
        Button(onClick = { setCount(count + 1) }, text = "+")
    )
}

fun main() {
    ReactDom.render(counter(), document.getElementById("app"))
}
