// Flutter widget example
import 'package:flutter/material.dart';

class UserProfileWidget extends StatefulWidget {
  final String userId;
  
  const UserProfileWidget({Key? key, required this.userId}) : super(key: key);
  
  @override
  State<UserProfileWidget> createState() => _UserProfileWidgetState();
}

class _UserProfileWidgetState extends State<UserProfileWidget> {
  late Future<UserProfile> _profileFuture;
  
  @override
  void initState() {
    super.initState();
    _profileFuture = _loadProfile();
  }
  
  Future<UserProfile> _loadProfile() async {
    final response = await ApiService.getProfile(widget.userId);
    return UserProfile.fromJson(response.data);
  }
  
  @override
  Widget build(BuildContext context) {
    return FutureBuilder<UserProfile>(
      future: _profileFuture,
      builder: (context, snapshot) {
        if (snapshot.connectionState == ConnectionState.waiting) {
          return const CircularProgressIndicator();
        }
        
        if (snapshot.hasError) {
          return Text('Error: ${snapshot.error}');
        }
        
        final profile = snapshot.data!;
        return Column(
          children: [
            Avatar(imageUrl: profile.avatarUrl),
            Text(profile.name),
            Text(profile.email),
          ],
        );
      },
    );
  }
}

class UserProfile {
  final String name;
  final String email;
  final String avatarUrl;
  
  UserProfile({
    required this.name,
    required this.email,
    required this.avatarUrl,
  });
  
  factory UserProfile.fromJson(Map<String, dynamic> json) {
    return UserProfile(
      name: json['name'] as String,
      email: json['email'] as String,
      avatarUrl: json['avatar_url'] as String? ?? '',
    );
  }
  
  Map<String, dynamic> toJson() => {
    'name': name,
    'email': email,
    'avatar_url': avatarUrl,
  };
}

class ApiService {
  static const String _baseUrl = 'https://api.example.com';
  
  static Future<ApiResponse> getProfile(String userId) async {
    final url = '$_baseUrl/users/$userId';
    // HTTP call implementation
    return ApiResponse(data: {});
  }
}

class ApiResponse {
  final Map<String, dynamic> data;
  
  ApiResponse({required this.data});
}
