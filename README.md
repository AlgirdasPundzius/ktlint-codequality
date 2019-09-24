# ktlint-codequality
[Ktlint](https://github.com/pinterest/ktlint) extension to report in [gitlab code quality standard](https://docs.gitlab.com/ee/user/project/merge_requests/code_quality.html) and get this nice tab in your merge requests.
![Gitlab Code quality tab](https://i.ibb.co/qnKnpCS/code-quality.jpg)

## Installation
Base [Ktlint](https://github.com/pinterest/ktlint) is required.

Add `ktlint 'pm.algirdas.ktlint:reporter:0.2.1'` in your build.gradle

Project is available in jCenter

## Usage
Use `gitlab-quality` reporter for ktlint `args "--reporter=gitlab-quality,output=${buildDir}/ktquality.json"`

Have a job in your gitlab .yml for reports similar to this
```
ktlint:
  script:
    - ./gradlew ktlint
  artifacts:
    reports:
        codequality: app/build/ktquality.json```

After that just enjoy your nice reports in future merge requests.