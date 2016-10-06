package com.stg.helpers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.stg.models.Profile;
import com.stg.models.Task;
import com.stg.models.TaskLabel;
import com.stg.models.TaskTemplate;

public class TaskTemplateConverter {

    /**
     * Convert a list of TaskTemplates and assign the profile to each task
     *
     * @param taskTemplateList
     * @param userProfile
     * @return List<Task>
     */
    public static List<Task> convertList(List<TaskTemplate> taskTemplateList, Profile userProfile) {
	List<Task> taskList = new ArrayList<>();

	for (TaskTemplate temp : taskTemplateList) {
	    Task task = new Task();
	    task.setCategory(temp.getCategory());
	    task.setTask(temp.getTask());
	    task.setProfile(userProfile);

	    Set<TaskLabel> labelSet = new HashSet<TaskLabel>();
	    for (TaskLabel label : temp.getLabels()) {
		labelSet.add(label);
	    }
	    task.setLabels(labelSet);

	    taskList.add(task);
	}

	return taskList;
    }
}
