def test_folders = ['src\\tests', 'src\\sprint-tests']
def testFolders, sprintTestFolders
def testsPath

node('ui-slave'){
git(
       url: 'https://syn-gitlab.tms-orbcomm.com:8800/Software/NextGen/IoTStack/SaaS/FleetManager/fleetmanager-nightwatch.git',
       credentialsId: 'xpc',
       branch: '${workspace}'
    )
stage('Setup Jobs List'){
    println "Workspace Environment = ${workspace}"
    print "Tests Files Path:"+ pwd() + "\\" + test_folders[0]
    print "Sprint Tests Files Path:"+ pwd() + "\\" + test_folders[1]
    testFolders = getAllFiles(createFilePath(pwd() + "\\" + test_folders[0]))
    sprintTestFolders = getAllFiles(createFilePath(pwd() + "\\" + test_folders[1]))
    print "Tests Folders:"+ testFolders
    print "Sprint Tests Folders:"+ sprintTestFolders
}
stage('Run FleetManager UI Tests') {
   		for (int i = 0; i < testFolders.size; i++) {
        testsPath = test_folders[0]+"\\"+testFolders[i]
    		stage("Stage - ${testFolders[i]}") {
	        	build job: 'Configurable Test Runner', parameters: [[$class: 'StringParameterValue', name: 'test path', value: testsPath], [$class: 'StringParameterValue', name: 'browser', value: "${browser}"], [$class: 'StringParameterValue', name: 'tags', value: "${tags}"], [$class: 'StringParameterValue', name: 'workspace', value: JOB_NAME]], propagate: false
        	}
    	}
    	for (int i = 0; i < sprintTestFolders.size; i++) {
        testsPath = test_folders[1]+"\\"+sprintTestFolders[i]
    		stage("Stage - ${sprintTestFolders[i]}") {
	        	build job: 'Configurable Test Runner', parameters: [[$class: 'StringParameterValue', name: 'test path', value: testsPath], [$class: 'StringParameterValue', name: 'browser', value: "${browser}"], [$class: 'StringParameterValue', name: 'tags', value: "${tags}"],  [$class: 'StringParameterValue', name: 'workspace', value: JOB_NAME]], propagate: false
        	}
    	}
	}
}
@NonCPS
def getAllFiles(rootPath) {
    def list = []
    for (subPath in rootPath.list()) {
        list << subPath.getName()
    }
    return list
}

// Helps if slave servers are in picture
def createFilePath(def path) {
    if (env['NODE_NAME'].equals("master")) {
        File localPath = new File(path)
        return new hudson.FilePath(localPath);
    } else {
        return new hudson.FilePath(Jenkins.getInstance().getComputer(env['NODE_NAME']).getChannel(), path);
    }
}
