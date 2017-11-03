set "command=./node_modules/.bin/nightwatch 

set "gr= --group "
set "skipgr= --skipgroup "
set "tag= --tag "
set "skip= --skiptags "
set "en= -e "
set "ret= --retries "

IF NOT [%groups%]==[] set "command=%command%%gr%%groups%"
IF NOT [%skip_groups%]==[] set "command=%command%%skipgr%%skip_groups%"
IF NOT [%run_tags%]==[] set "command=%command%%tag%%run_tags%"
IF NOT [%skip_tags%]==[] set "command=%command%%skip%%skip_tags%"
IF NOT [%env%]==[] set "command=%command%%en%%env%"
IF NOT [%retries%]==[] set "command=%command%%ret%%retries%"


%command%