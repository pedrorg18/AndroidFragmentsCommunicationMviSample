# AndroidFragmentsCommunicationMviSample
## Summary
App that implements a user list and user detail.

## Implementation / architecure details
It's implemented with an Activity that acts as Fragment container and launcher.

The communication is done trough the ViewModel, which contains a LiveData for each viewElement.

The data is laid out in MVI fashion, sending a ViewState tailored to each View element.

## Diagram
At the project root there is an image depicting the architecture and the various relations between actors
