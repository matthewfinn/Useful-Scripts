set "command=./node_modules/.bin/nightwatch -e %env%"

set "gr= --group "
set "skipgr = --skipgroup "
set "tag= --tag "
set "skip = --skiptags "
IF NOT [%groups%]==[] set "command=%command%%gr%groups%"
IF NOT [%skip_groups%]==[] set "command=%command%%skipgr%skip_groups%"
IF NOT [%tags%]==[] set "command=%command%%tag%tags%"
IF NOT [%skip_tags%]==[] set "command=%command%%skip%skip_tags%"

%command%