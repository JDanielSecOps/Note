

#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <gtk/gtk.h>
#include <gobject/gsignal.h>
#include <time.h>

#include <string.h>
#include <unistd.h>
#include <stdbool.h>



enum{

    TASK,
    TASK_DESCRIPTION,
    CREATED_DATE,
    DEADLINE_DATE,
    COMPLETED,
    NUM_COLUMNS
};


void on_add_todo_button_clicked(GtkButton * button, gpointer user_data);
void on_delete_todo_button_clicked(GtkButton * button, gpointer user_data);
void on_update_todo_button_clicked(GtkButton * button, gpointer user_data);
void on_unselect_todo_button_clicked(GtkButton * button, gpointer user_data);


void on_dialog_cancel_clicked(GtkButton * button, gpointer user_data);
void on_dialog_submit_clicked(GtkButton * button, gpointer user_data);
void on_dialog_submit_update_clicked(GtkButton * button, gpointer user_data);

void set_form();
void custom_set_form();
void on_completed_renderer_toggled(GtkCellRendererToggle * cell_renderer,gchar * path_str);
static gboolean on_delete_dialog(GtkWidget * widget , GdkEvent * event);



void file_read();
void file_write();
bool is_file_empty(FILE * fptr);
bool is_file_present();

//Main

GtkWidget * Main_window;
GtkWidget * grid_fixed_1;
GtkWidget * add_todo_button;
GtkWidget * delete_todo_button;
GtkWidget * update_todo_button;
GtkWidget * unselect_todo_button;

// Dialog
GtkDialog  * data_entry;

// Dialog Buttons
GtkWidget * dialog_submit;
GtkWidget * dialog_cancel;

// Dialog Inputs

GtkWidget * task_name;
GtkWidget * task_description;
GtkWidget * date;
GtkWidget * time_hour;
GtkWidget * time_minutes;
GtkWidget * time_seconds;
GtkWidget * am_or_pm;


//Table

GtkListStore * todo_store;
GtkTreeView  * table;

GtkCellRenderer * task_renderer;
GtkCellRenderer * task_description_renderer;
GtkCellRenderer * created_renderer;
GtkCellRenderer * deadline_renderer;
GtkCellRenderer * completed_renderer;

GtkTreeViewColumn * task_column;
GtkTreeViewColumn * task_description_column;
GtkTreeViewColumn * created_column;
GtkTreeViewColumn * deadline_column;
GtkTreeViewColumn * completed_column;

GtkTreeSelection * selection;

//Builder
GtkBuilder  * builder;



int main(int argc , char * argv []){

    
    gtk_init(&argc,&argv);

    builder = gtk_builder_new_from_file("todo_ui.glade");
    
    
    Main_window =GTK_WIDGET(gtk_builder_get_object(builder,"Main_window"));
    
    g_signal_connect(Main_window,"destroy",G_CALLBACK(gtk_main_quit),NULL);
    

    
    
    
    gtk_builder_connect_signals(builder,NULL);

     


    //Main UI
    grid_fixed_1=GTK_WIDGET(gtk_builder_get_object(builder,"grid_fixed_1"));
    add_todo_button =GTK_WIDGET(gtk_builder_get_object(builder,"add_todo_button"));
    update_todo_button =GTK_WIDGET(gtk_builder_get_object(builder,"update_todo_button"));
    delete_todo_button =GTK_WIDGET(gtk_builder_get_object(builder,"delete_todo_button"));
    unselect_todo_button=GTK_WIDGET(gtk_builder_get_object(builder,"unselect_todo_button"));

    //dialog
    data_entry =GTK_DIALOG(gtk_builder_get_object(builder,"data_entry"));
    g_signal_connect(data_entry,"delete-event",G_CALLBACK(on_delete_dialog),NULL);

    //dialog buttons

    dialog_submit=GTK_WIDGET(gtk_builder_get_object(builder,"dialog_submit"));
    dialog_cancel=GTK_WIDGET(gtk_builder_get_object(builder,"dialog_cancel"));

    
    //dialog inputs

    task_name = GTK_WIDGET(gtk_builder_get_object(builder,"task_name"));
    task_description = GTK_WIDGET(gtk_builder_get_object(builder,"task_description"));
    date = GTK_WIDGET(gtk_builder_get_object(builder,"date"));
    time_hour = GTK_WIDGET(gtk_builder_get_object(builder,"time_hour"));
    time_minutes = GTK_WIDGET(gtk_builder_get_object(builder,"time_minutes"));
    time_seconds =GTK_WIDGET(gtk_builder_get_object(builder,"time_seconds"));
    am_or_pm =GTK_WIDGET(gtk_builder_get_object(builder,"am_or_pm"));


    //Table

    todo_store =GTK_LIST_STORE(gtk_builder_get_object(builder,"todo_store"));
    table =GTK_TREE_VIEW(gtk_builder_get_object(builder,"table"));

    
    task_column=GTK_TREE_VIEW_COLUMN(gtk_builder_get_object(builder,"task_column"));
    task_description_column=GTK_TREE_VIEW_COLUMN(gtk_builder_get_object(builder,"task_description_column"));
    created_column=GTK_TREE_VIEW_COLUMN(gtk_builder_get_object(builder,"created_column"));
    deadline_column=GTK_TREE_VIEW_COLUMN(gtk_builder_get_object(builder,"deadline_column"));
    completed_column=GTK_TREE_VIEW_COLUMN(gtk_builder_get_object(builder,"completed_column"));


    task_renderer=GTK_CELL_RENDERER(gtk_builder_get_object(builder,"task_renderer"));
    task_description_renderer=GTK_CELL_RENDERER(gtk_builder_get_object(builder,"task_description_renderer"));
    created_renderer=GTK_CELL_RENDERER(gtk_builder_get_object(builder,"created_renderer"));
    deadline_renderer=GTK_CELL_RENDERER(gtk_builder_get_object(builder,"deadline_renderer"));
    completed_renderer=GTK_CELL_RENDERER(gtk_builder_get_object(builder,"completed_renderer"));


    gtk_tree_view_column_add_attribute(task_column,task_renderer,"text",0);
    gtk_tree_view_column_add_attribute(task_description_column,task_description_renderer,"text",1);
    gtk_tree_view_column_add_attribute(created_column,created_renderer,"text",2);
    gtk_tree_view_column_add_attribute(deadline_column,deadline_renderer,"text",3);
    gtk_tree_view_column_add_attribute(completed_column,completed_renderer,"active",4);
    
    selection =gtk_tree_view_get_selection(GTK_TREE_VIEW(table));

    file_read();

    gtk_widget_show_all(Main_window);
    
    gtk_main();

    

    return  EXIT_SUCCESS;

}




void on_add_todo_button_clicked(GtkButton * button, gpointer user_data){

    //g_print(" Add todo button clicked \n");

    gtk_window_set_decorated(GTK_DIALOG(data_entry),FALSE);
    gtk_window_maximize(GTK_WINDOW(Main_window));
    set_form();
    gtk_widget_show(GTK_WIDGET(data_entry));
    


}

void on_dialog_cancel_clicked(GtkButton * button, gpointer user_data){

    gtk_widget_hide(GTK_WIDGET(data_entry));
}   

void on_dialog_submit_clicked(GtkButton * button, gpointer user_data){

    GtkTreeIter iter;
    GtkTreeModel * model;
    


    guint year,month,day;
    gchar deadline[50];
    gchar created[50];

    time_t time_now = time(NULL);
    struct tm * local = localtime(&time_now);



    const gchar * Task =gtk_entry_get_text(task_name);
    const gchar * Task_Description =gtk_entry_get_text(task_description);

    int hours =gtk_spin_button_get_value_as_int(GTK_SPIN_BUTTON(time_hour));
    int minutes =gtk_spin_button_get_value_as_int(GTK_SPIN_BUTTON(time_minutes));
    int seconds =gtk_spin_button_get_value_as_int(GTK_SPIN_BUTTON(time_seconds));

    gtk_calendar_get_date(GTK_CALENDAR(date),&year,&month,&day);
    const gchar * day_or_night =gtk_combo_box_text_get_active_text(GTK_COMBO_BOX_TEXT(am_or_pm));

    
    if(strlen(Task) ==0 || strlen(Task_Description) ==0){
        return;
    }

   
    strftime(created,sizeof(created),"%d.%m.%Y %I:%M:%S %p",local);
    snprintf(deadline,sizeof(deadline),"%02d.%02d.%04d %02d:%02d:%02d %s",
    day,month+1,year,
    hours,minutes,seconds,day_or_night
    );

    if(gtk_tree_selection_get_selected(selection,&model,&iter)){


    gtk_list_store_set(
        todo_store,&iter,
        TASK,Task,
        TASK_DESCRIPTION,Task_Description,
        DEADLINE_DATE,deadline,-1
    );

    file_write();

    }else{


    gtk_list_store_append(todo_store,&iter);
    gtk_list_store_set(
        todo_store,&iter,
        TASK,Task,
        TASK_DESCRIPTION,Task_Description,
        CREATED_DATE,created,
        DEADLINE_DATE,deadline,
        COMPLETED,FALSE,-1
    );

    file_write();

    }

 


  g_free(day_or_night);

    //g_print(" Task             : %s \n",Task);
    //g_print(" Task Description : %s \n",Task_Descriptigpon);
    //g_print(" Deadline         : %d.%d.%d \n",day,month+1,year);
    //g_print(" Time             : %d:%d:%d %s \n",hours,minutes,seconds,day_or_night);
    //g_print("\n");

    gtk_widget_hide(GTK_WIDGET(data_entry));
    gtk_tree_selection_unselect_all(selection);
}


void set_form(){

    time_t time_now = time(NULL);
    struct tm * local = localtime(&time_now);

    gtk_entry_set_text(GTK_ENTRY(task_name),"");
    gtk_entry_set_text(GTK_ENTRY(task_description),"");


    gtk_calendar_select_month(GTK_CALENDAR(date),local->tm_mon,local->tm_year+1900);
    gtk_calendar_select_day(GTK_CALENDAR(date),local->tm_mday);

    gtk_spin_button_set_value(GTK_SPIN_BUTTON(time_hour),(local->tm_hour % 12 ==0) ? 12 : local->tm_hour % 12);
    gtk_spin_button_set_value(GTK_SPIN_BUTTON(time_minutes),local->tm_min);
    gtk_spin_button_set_value(GTK_SPIN_BUTTON(time_seconds),local->tm_sec);

    gtk_combo_box_set_active(GTK_COMBO_BOX(am_or_pm),(local->tm_hour  <12) ? 0 : 1);

}


static gboolean on_delete_dialog(GtkWidget * widget , GdkEvent * event){
    
    gtk_widget_hide(GTK_WIDGET(data_entry));
    return TRUE;
}

void on_completed_renderer_toggled(GtkCellRendererToggle * cell_renderer,gchar * path_str){

    GtkTreePath * path = gtk_tree_path_new_from_string(path_str);
    GtkTreeIter iter;
    gboolean active;

    if(gtk_tree_model_get_iter(GTK_TREE_MODEL(todo_store),&iter,path)){
        
        gtk_tree_model_get(GTK_TREE_MODEL(todo_store),&iter,COMPLETED,&active,-1);
        gtk_list_store_set(todo_store,&iter,COMPLETED,!active,-1);
        gtk_tree_selection_unselect_all(selection);
        file_write();
    }

    gtk_tree_path_free(path);

}


void on_delete_todo_button_clicked(GtkButton * button, gpointer user_data){

    GtkTreeIter iter;
    GtkTreeModel * model;
    

    if(gtk_tree_selection_get_selected(selection,&model,&iter)){

        gtk_list_store_remove(GTK_LIST_STORE(model),&iter);
        gtk_tree_selection_unselect_all(selection);
        file_write();
    }
    
    
}

void on_update_todo_button_clicked(GtkButton * button, gpointer user_data){

    

    GtkTreeIter iter;
    GtkTreeModel * model;




    

    if(gtk_tree_selection_get_selected(selection,&model,&iter)){




        gchar*task;
        gchar*task_description_data;
        gchar* created;
        gchar* deadline;

         

        gtk_window_set_decorated(GTK_DIALOG(data_entry),FALSE);
        gtk_tree_model_get(model,&iter,TASK
        ,&task,TASK_DESCRIPTION,&task_description_data,CREATED_DATE,&created,DEADLINE_DATE,&deadline,-1);
        custom_set_form(task,task_description_data,deadline);

        if(data_entry==NULL){
            g_print("THIS IS NULL");
        }

        gtk_widget_show(GTK_WIDGET(data_entry));


    }


    


}

void custom_set_form(gchar * task,gchar * task_description_data,gchar * deadline){

    int day,month,year;
    int hours,minutes,seconds;
    char time_period[3];

    int result =sscanf(deadline,"%d.%d.%d %d:%d:%d %2s",&day,&month,&year,&hours,&minutes,&seconds,&time_period);



    gtk_entry_set_text(GTK_ENTRY(task_name),task);
    
    gtk_entry_set_text(GTK_ENTRY(task_description),task_description_data);

    
    gtk_calendar_select_month(GTK_CALENDAR(date),month-1,year);
   
    gtk_calendar_select_day(GTK_CALENDAR(date),day);

    gtk_spin_button_set_value(GTK_SPIN_BUTTON(time_hour),hours);
    gtk_spin_button_set_value(GTK_SPIN_BUTTON(time_minutes),minutes);
    
    gtk_spin_button_set_value(GTK_SPIN_BUTTON(time_seconds),seconds);

 

    if(g_ascii_strncasecmp(time_period,"AM",2)==0){


        gtk_combo_box_set_active(GTK_COMBO_BOX(am_or_pm),0);

    }else if (g_ascii_strncasecmp(time_period,"PM",2)==0){



        gtk_combo_box_set_active(GTK_COMBO_BOX(am_or_pm),1);

    }else{


        
        gtk_combo_box_set_active(GTK_COMBO_BOX(am_or_pm),-1);

    }


}


void on_unselect_todo_button_clicked(GtkButton * button, gpointer user_data){

    gtk_tree_selection_unselect_all(selection);
}



bool is_file_present(){

    FILE * fptr;

    if(access("file.txt",F_OK)==0){
        return true;
    }else{
        fptr=fopen("file.txt","w");
        fclose(fptr);
        return false;
    }

}

bool is_file_empty(FILE * fptr){

    fseek(fptr,0,SEEK_END);
    long size =ftell(fptr);

    if(size==0){
        fseek(fptr,0,SEEK_SET);
        return true;
    }else{
        fseek(fptr,0,SEEK_SET);
        return false;
    }

}

void file_write(){

    is_file_present();

    FILE * fptr =fopen("file.txt","w");

    


    GtkTreeIter iter;
    gboolean valid =gtk_tree_model_get_iter_first(GTK_TREE_MODEL(todo_store),&iter);





    while(valid){

        gchar*task;
        gchar*task_description_data;
        gchar* created;
        gchar* deadline;
        gboolean completed;

        gtk_tree_model_get(GTK_TREE_MODEL(todo_store),&iter
        ,TASK,&task,TASK_DESCRIPTION,&task_description_data,
        CREATED_DATE,&created,DEADLINE_DATE,&deadline,COMPLETED,&completed,-1);
        fprintf(fptr,"%s,%s,%s,%s,%d\n",task,task_description_data,created,deadline,completed);

        g_free(task);
        g_free(task_description_data);
        g_free(created);
        g_free(deadline);

        valid =gtk_tree_model_iter_next(GTK_TREE_MODEL(todo_store),&iter);
    }

    

    fclose(fptr);

}


void file_read(){

    is_file_present();

    FILE * fptr =fopen("file.txt","r");


    if(is_file_empty(fptr)){
        fclose(fptr);
        return;
    }

    char buffer[400];

    GtkTreeIter iter;


    // printf("\n");

    while(fgets(buffer,sizeof(buffer),fptr)!=NULL){

        buffer[strcspn(buffer,"\n")]='\0';

        gchar * task=strtok(buffer,",");
        gchar * task_description=strtok(NULL,",");
        gchar * completed_date=strtok(NULL,",");
        gchar * deadline_date=strtok(NULL,",");
        gchar * is_completed=strtok(NULL,",");

        gboolean completed =(g_strcmp0(is_completed,"1") ==0 || g_ascii_strcasecmp(is_completed,"true")==0);

        // printf("task : %s \n",task);
        // printf("task desription: %s \n",task_description);
        // printf("completed deadline : %s \n",completed_date);
        // printf("deadline date : %s \n",deadline_date);
        // printf("completed : %d \n",completed);
        // printf("\n");

        gtk_list_store_append(todo_store,&iter);
        gtk_list_store_set(
            todo_store,&iter,
            TASK,task,
            TASK_DESCRIPTION,task_description,
            CREATED_DATE,completed_date,
            DEADLINE_DATE,deadline_date,
            COMPLETED,completed,-1
        );



    }

    fclose(fptr);

}   