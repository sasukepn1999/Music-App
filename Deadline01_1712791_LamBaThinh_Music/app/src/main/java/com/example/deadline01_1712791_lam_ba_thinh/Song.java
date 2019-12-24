package com.example.deadline01_1712791_lam_ba_thinh;

public class Song {
    private String m_Name;
    private String m_avatarName;
    private String m_description;

    public Song(String name, String avatarName, String description){
        this.m_Name = name;
        this.m_avatarName = avatarName;
        this.m_description = description;
    }

    public String getM_Name(){  return this.m_Name; }

    public void setM_Name(String m_Name) { this.m_Name = m_Name; }

    public String getM_avatarName(){ return this.m_avatarName; }

    public void setM_avatarName(String m_avatarName) { this.m_avatarName = m_avatarName; }

    public String getM_description(){ return this.m_description; }

    public void setM_description(String m_description) { this.m_description = m_description; }
}
