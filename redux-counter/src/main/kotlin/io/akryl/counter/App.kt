package io.akryl.counter

import io.akryl.component
import io.akryl.dom.html.button
import io.akryl.dom.html.div
import io.akryl.dom.html.h2
import io.akryl.redux.*
import react_dom.ReactDom
import redux.StoreEnhancer
import kotlin.browser.document
import kotlin.browser.window

sealed class Msg {
    object Increment : Msg()
    object Decrement : Msg()
    object Alert : Msg()
}

sealed class Cmd {
    class Alert(val message: String) : Cmd()
}

val devTools = js("window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__()")
    .unsafeCast<StoreEnhancer<Int, MsgAction<Msg>>>()

val store = createStore(
    init = Pair(0, null),
    update = { state: Int, msg: Msg ->
        when (msg) {
            is Msg.Increment -> Pair(state + 1, null)
            is Msg.Decrement -> Pair(state - 1, null)
            is Msg.Alert -> Pair(state, Cmd.Alert("Count = $state"))
        }
    },
    execute = { cmd: Cmd, _ ->
        when (cmd) {
            is Cmd.Alert -> {
                window.alert(cmd.message)
            }
        }
    },
    enhancer = devTools
)

fun counter() = component {
    val count = useSelector<Int>()
    val dispatch = useDispatch<Msg>()

    div(
        h2(text = "Count: $count"),
        button(onClick = { dispatch(Msg.Decrement) }, text = "-"),
        button(onClick = { dispatch(Msg.Increment) }, text = "+"),
        button(onClick = { dispatch(Msg.Alert) }, text = "alert")
    )
}

fun app() = component {
    store.provider(children = listOf(
        counter()
    ))
}

fun main() {
    ReactDom.render(app(), document.getElementById("app"))
}
