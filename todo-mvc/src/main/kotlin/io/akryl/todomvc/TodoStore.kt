package io.akryl.todomvc

import io.akryl.BuildContext
import io.akryl.rx.computed
import io.akryl.store.Store

data class Task(
  var id: String,
  var title: String,
  var completed: Boolean
)

data class TaskFilter(
  val name: String,
  val url: String,
  val predicate: (Task) -> Boolean
)

class TodoStore : Store() {
  companion object {
    fun of(context: BuildContext) = of<TodoStore>(context)
  }

  var tasks = arrayListOf(
    Task(id = uuid4(), title = "Task 1", completed = false),
    Task(id = uuid4(), title = "Task 2", completed = true),
    Task(id = uuid4(), title = "Task 3", completed = false)
  )

  val filters = listOf(
    TaskFilter(
      name = "All",
      url = "/",
      predicate = { true }
    ),
    TaskFilter(
      name = "Active",
      url = "/active",
      predicate = { !it.completed }
    ),
    TaskFilter(
      name = "Completed",
      url = "/completed",
      predicate = { it.completed }
    )
  )

  var filter: TaskFilter = filters[0]

  val filtered by computed { tasks.filter(filter.predicate) }

  val allCompleted by computed { tasks.all { it.completed } }

  fun add(title: String) {
    tasks.add(Task(
      id = uuid4(),
      title = title,
      completed = false
    ))
    println(tasks)
  }

  fun toggle(id: String) {
    val task = tasks.first { it.id == id }
    task.completed = !task.completed
  }

  fun clearCompleted() {
    tasks.removeAll { it.completed }
  }

  fun delete(id: String) {
    tasks.removeAt(tasks.indexOfFirst { it.id == id })
  }

  fun toggleAll() {
    val value = !allCompleted
    tasks.forEach { it.completed = value }
  }

  fun rename(id: String, title: String) {
    val task = tasks.first { it.id == id }
    task.title = title
  }
}

@JsModule("uuid/v4")
external fun uuid4(): String
