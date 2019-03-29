package com.radiant.rpl.testa.ExamSection;

public class SetterGetter {
    String selected_answer,que_id,student_id,status_id;

    SetterGetter(){
    }

    public SetterGetter(String student_id, String que_id, String selected_answer, String status_id) {
        this.student_id = student_id;
        this.que_id = que_id;
        this.selected_answer = selected_answer;
        this.status_id=status_id;
    }

    public String getStudent_id() {
        return student_id;
    }


    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getQue_id() {
        return que_id;
    }

    public void setQue_id(String que_id) {
        this.que_id = que_id;
    }

    public String getSelected_answer() {
        return selected_answer;
    }

    public void setSelected_answer(String selected_answer) {
        this.selected_answer = selected_answer;
    }

    public String getStatus_id(){
        return status_id;
    }

    public void setStatus_id(String status_id){
        this.status_id=status_id;
    }

}
