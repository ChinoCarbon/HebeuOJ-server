package org.chinocarbon.judgesystem.pojo;

import lombok.*;

/**
 * @author ChinoCarbon
 * @since 2022/5/13-2:44 PM
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SinglePage
{
    private int page;
    private int numPerPage;
    private String keyWord;
}
