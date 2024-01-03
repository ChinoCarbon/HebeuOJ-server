package org.chinocarbon.judgesystem.pojo;

import lombok.*;

import java.sql.Timestamp;

/**
 * @author ChinoCarbon
 * @since 2022/5/8-7:47 PM
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User
{
    private int userId;
    private String userName;
    private String userPassword;
    private String userEmail;
    private String userSignature;
    private int userSubmission;
    private int userPassed;
    private char userType;
    private Timestamp userRegisterTime;
    private int userClass;

    private String checkCode;
}
