import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MyAppStatefulPage();
  }
}

class MyAppStatefulPage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return MyAppState();
  }
}

class MyAppState extends State<MyAppStatefulPage> {
  static const channelName = 'flutter.bridge.call_platform';
  static const _platform = MethodChannel(channelName);

  String _hostAppMethodRst = '';

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        title: 'Flutter Demo',
        home: Scaffold(
            appBar: AppBar(
              title: Text('Flutter Module Page'),
            ),
            body: Container(
              width: double.infinity,
              padding: EdgeInsets.all(16),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.center,
                children: <Widget>[
                  RaisedButton(
                    child: Text('Call Host App'),
                    onPressed: () {
                      _platform.invokeMethod('testMethod').then((value) {
                        setState(() => _hostAppMethodRst = _hostAppMethodRst + '\n' + value);
                      }).catchError((e) {
                        setState(() => _hostAppMethodRst = _hostAppMethodRst + '\n' + e.message);
                      });
                    },
                  ),
                  Text('Respond from host App:\n $_hostAppMethodRst')
                ],
              ),
            )));
  }
}
