def browser = manager.build.buildVariables.get('env')
if(browser.empty){
     browser = 'firefox'
}
def groups= manager.build.buildVariables.get('groups')
if(groups.empty){
     groups= 'None Defined'
}
def skip_groups= manager.build.buildVariables.get('skip_groups')
if(skip_groups.empty){
     skip_groups= 'None Defined'
}
def run_tags= manager.build.buildVariables.get('run_tags')
if(run_tags.empty){
     run_tags= 'None Defined'
}
def skip_tags= manager.build.buildVariables.get('skip_tags')
if(skip_tags.empty){
     skip_tags= 'None Defined'
}
manager.addShortText("Browser: ${browser}", "grey", "white", "0px", "white")
manager.addShortText("Tags: ${run_tags}", "grey", "white", "0px", "white")
manager.addShortText("Skip Tags: ${skip_tags}", "grey", "white", "0px", "white")
manager.addShortText("Groups: ${groups}", "grey", "white", "0px", "white")
manager.addShortText("Skip Groups: ${skip_groups}", "grey", "white", "0px", "white")
def wkspce= manager.build.buildVariables.get('workspace')
manager.addShortText("Workspace: ${wkspce}", "grey", "white", "0px", "white")
