
#!/bin/bash

unset GTK_PATH

#; 


if gcc -rdynamic -g main.c -o "Todo App" $(pkg-config --cflags --libs gtk+-3.0); then

    ./'Todo App'
    echo "Build Successful"

else

    echo "Build Failed"

fi