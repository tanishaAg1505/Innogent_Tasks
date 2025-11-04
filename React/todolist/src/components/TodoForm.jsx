import React, { useEffect, useState } from "react";

export default function TodoForm({ onAdd, editTodo, onUpdate, onCancelEdit }) {
  const [text, setText] = useState("");

  useEffect(() => {
    if (editTodo) setText(editTodo.text);
    else setText("");
  }, [editTodo]);

  const submit = (e) => {
    e.preventDefault();
    if (!text.trim()) return;
    if (editTodo) {
      onUpdate(editTodo.id, text.trim());
    } else {
      onAdd(text.trim());
    }
    setText("");
  };

  return (
    <form className="todo-form" onSubmit={submit}>
      <input
        type="text"
        placeholder={editTodo ? "Edit task..." : "Add a new task..."}
        value={text}
        onChange={(e) => setText(e.target.value)}
      />
      <button type="submit">{editTodo ? "Update" : "Add"}</button>
      {editTodo && (
        <button type="button" className="cancel" onClick={() => { onCancelEdit(); setText(""); }}>
          Cancel
        </button>
      )}
    </form>
  );
}