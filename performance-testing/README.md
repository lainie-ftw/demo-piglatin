Included in this folder are screenshots of hitting the Pig Latin application endpoints - one serverless, one not serverless, both from the same image build. These are anecdotal results, but we thought they were interesting!

All of these use the hey binary, and assume 30 second of constant traffic.

concurrency1 refers to the serverless version of the application set up to only allow one request per pod, and concurrency5 refers to the serverless version of the application set up to only allow 5 requests per pod.

These screenshots are also included in the slides, with some additional context and conclusions.
