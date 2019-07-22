package io.akryl.todomvc

import io.akryl.*
import io.akryl.html.*
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event

data class TaskTile(val task: Task) : StatefulWidget(key = ValueKey(task.id)) {
  override fun style() = css {
    ".task" {
      borderTop = "1px solid lightgray"
    }

    ".view" {
      display = "flex"
      alignItems = "center"
    }

    ".title-input" {
      display = "none"
      padding = "12px 16px"
      margin = "0 0 0 44px"
      width = "calc(100% - 44px)"
      fontSize = "24px"
    }

    "label" {
      flex = "1 1 100%"
      wordBreak = "break-all"
      padding = "15px 15px 15px 0"
      display = "block"
      lineHeight = "1.2"
      transition = "color 0.4s"
      fontSize = 24.px
      margin = "0"
    }

    ".toggle" {
      flex = "0 0 28px"
      margin = "0 16px"
      borderRadius = "50%"
      border = "1px solid lightgray"
      height = 28.px
    }

    ".task.completed" {
      ".toggle" {
        borderColor = "gray"
      }

      ".toggle:after" {
        content = "'\\2713'"
        color = "green"
        width = 26.px
        display = "block"
        textAlign = "center"
        fontSize = 18.px
        marginTop = "-1px"
      }

      "label" {
        textDecoration = "line-through"
        color = "#d9d9d9"
      }
    }

    ".delete-button" {
      color = "#cc9a9a"
      background = "none"
      outline = "none"
      border = "none"
      visibility = "hidden"
      fontSize = "30px"
      marginRight = "12px"
    }

    ".task:hover" {
      ".delete-button" {
        visibility = "visible"
      }
    }

    ".task.editing" {
      ".view" {
        display = "none"
      }

      ".title-input" {
        display = "block"
      }
    }
  }

  override fun createState(context: BuildContext): State<*> = TaskTileState(context)
}

private class TaskTileState(context: BuildContext) : State<TaskTile>(context) {
  private val store = TodoStore.of(context)
  private var editing = false

  override fun build(context: BuildContext) = Div(
    classes = classMap("task" to true, "completed" to widget.task.completed, "editing" to editing),
    children = listOf(
      Div(clazz = "view", children = listOf(
        Div(clazz = "toggle", onClick = { store.toggle(widget.task.id) }),
        Label(child = Text(widget.task.title), onDoubleClick = { editing = true }),
        Button(clazz = "delete-button", child = Text("×"), onClick = { store.delete(widget.task.id) })
      )),
      Input(clazz = "title-input", value = widget.task.title, onBlur = this::rename)
    )
  )

  private fun rename(event: Event) {
    val value = (event.target as HTMLInputElement).value
    store.rename(widget.task.id, value)
    editing = false
  }
}
