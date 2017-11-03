stage('Download Regression Repository'){
    build job: 'Workspace - Regression'
}
stage('Run Regression Repository') {
    build job: 'Test Suite - Configurable', parameters: [[$class: 'StringParameterValue', name: 'skip_groups', value: 'reports'], [$class: 'StringParameterValue', name: 'workspace', value: 'Workspace - Regression']], propagate: false
}
stage('Download QATest1 Repository'){
    build job: 'Workspace - QATest1'
}
stage('Reports Tests On qatest1') {
    build job: 'Test Suite - Configurable', parameters: [[$class: 'StringParameterValue', name: 'groups', value: 'reports'], [$class: 'StringParameterValue', name: 'workspace', value: 'Workspace - QATest1']], propagate: false
}
