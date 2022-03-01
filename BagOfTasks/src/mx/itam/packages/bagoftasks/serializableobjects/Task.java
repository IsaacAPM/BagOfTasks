package mx.itam.packages.bagoftasks.serializableobjects;

import java.io.Serializable;

public class Task implements Serializable {
    private String taskId;
    private String requirementId;
    private int length;
    private String output;

    public Task(String taskId, String requirementId, int length) {
        this.taskId = taskId;
        this.requirementId = requirementId;
        this.length = length;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(String requirementId) {
        this.requirementId = requirementId;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId='" + taskId + '\'' +
                ", requirementId='" + requirementId + '\'' +
                ", length=" + length +
                ", output='" + output + '\'' +
                '}';
    }
}
