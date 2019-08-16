package io.akryl.todomvc.entities

import kotlinx.serialization.Serializable

@Serializable
data class Task(
  var id: String,
  var title: String,
  var completed: Boolean
)