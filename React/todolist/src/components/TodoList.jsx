import React from "react";
import TodoItem from "./TodoItem";

export default function TodoList({ todos, onEdit, onDelete }) {
  if (!todos || todos.length === 0) {
    return <p className="empty">No tasks yet. Add your first task!</p>;
  }
  return (
    <ul className="todo-list">
      {todos.map((t) => (
        <TodoItem key={t.id} todo={t} onEdit={() => onEdit(t)} onDelete={() => onDelete(t.id)} />
      ))}
    </ul>
  );
}