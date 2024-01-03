package org.chinocarbon.judgesystem.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;

import java.util.List;

/**
 * @author ChinoCarbon
 * @since 2022/6/21-7:38 PM
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Classes
{
    @TableId
    private int classId;
    private String className;
    private int classTeacher;

    List<User> students;
}
