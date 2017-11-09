# tanchc
###### \java\systemtests\EditTaskCommandSystemTest.java
``` java
        /* Case: edit some fields -> edited */
        index = INDEX_FIRST_TASK;
        command = EditTaskCommand.COMMAND_WORD + " " + index.getOneBased() + DATE_DESC_MEETING;
        ReadOnlyTask taskToEdit = getModel().getFilteredTaskList().get(index.getZeroBased());
        editedTask = new TaskBuilder(taskToEdit).withDate(VALID_DATE_MEETING).build();
        assertCommandSuccess(command, index, editedTask);
```
