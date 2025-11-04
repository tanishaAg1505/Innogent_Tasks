import React from "react";

export default function TodoItem({ todo, onEdit, onDelete }) {
  return (
    <li className="todo-item">
      <span className="todo-text">{todo.text}</span>
      <div className="actions">
        <button className="edit" onClick={onEdit}>Edit</button>
        <button className="delete" onClick={onDelete}>Delete</button>
      </div>
    </li>
  );
}
