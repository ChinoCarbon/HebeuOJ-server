package org.chinocarbon.judgesystem.pojo;

import lombok.*;

import java.util.List;

/**
 * @author ChinoCarbon
 * @since 2022/6/22-8:52 AM
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Homework
{
    private int homeworkId;
    private String homeworkTitle;
    private List<Integer> problems;
    private List<Integer> classes;
    private int homeworkTeacher;
}
