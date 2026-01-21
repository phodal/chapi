using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace ModernCSharp.Examples
{
    // ASP.NET Core Controller Example
    [ApiController]
    [Route("api/[controller]")]
    public class UsersController : ControllerBase
    {
        private readonly IUserService _userService;
        private readonly ILogger<UsersController> _logger;
        
        public UsersController(IUserService userService, ILogger<UsersController> logger)
        {
            _userService = userService;
            _logger = logger;
        }
        
        [HttpGet]
        public async Task<ActionResult<IEnumerable<UserDto>>> GetUsers(
            [FromQuery] int page = 1,
            [FromQuery] int pageSize = 10)
        {
            try
            {
                var users = await _userService.GetUsersAsync(page, pageSize);
                return Ok(users);
            }
            catch (Exception ex)
            {
                _logger.LogError(ex, "Error fetching users");
                return StatusCode(500, "Internal server error");
            }
        }
        
        [HttpGet("{id}")]
        public async Task<ActionResult<UserDto>> GetUser(Guid id)
        {
            var user = await _userService.GetUserByIdAsync(id);
            
            if (user is null)
            {
                return NotFound();
            }
            
            return Ok(user);
        }
        
        [HttpPost]
        public async Task<ActionResult<UserDto>> CreateUser([FromBody] CreateUserRequest request)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }
            
            var user = await _userService.CreateUserAsync(request);
            return CreatedAtAction(nameof(GetUser), new { id = user.Id }, user);
        }
    }
    
    // Service Layer with LINQ and Async
    public class UserService : IUserService
    {
        private readonly IUserRepository _repository;
        private readonly IEmailService _emailService;
        
        public UserService(IUserRepository repository, IEmailService emailService)
        {
            _repository = repository;
            _emailService = emailService;
        }
        
        public async Task<IEnumerable<UserDto>> GetUsersAsync(int page, int pageSize)
        {
            var users = await _repository.GetAllAsync();
            
            return users
                .Where(u => u.IsActive)
                .OrderBy(u => u.CreatedAt)
                .Skip((page - 1) * pageSize)
                .Take(pageSize)
                .Select(u => new UserDto
                {
                    Id = u.Id,
                    Username = u.Username,
                    Email = u.Email,
                    FullName = $"{u.FirstName} {u.LastName}"
                })
                .ToList();
        }
        
        public async Task<UserDto?> GetUserByIdAsync(Guid id)
        {
            var user = await _repository.GetByIdAsync(id);
            
            return user is not null
                ? MapToDto(user)
                : null;
        }
        
        public async Task<UserDto> CreateUserAsync(CreateUserRequest request)
        {
            var user = new User
            {
                Id = Guid.NewGuid(),
                Username = request.Username,
                Email = request.Email,
                FirstName = request.FirstName,
                LastName = request.LastName,
                CreatedAt = DateTime.UtcNow,
                IsActive = true
            };
            
            await _repository.AddAsync(user);
            await _emailService.SendWelcomeEmailAsync(user.Email);
            
            return MapToDto(user);
        }
        
        private static UserDto MapToDto(User user) => new()
        {
            Id = user.Id,
            Username = user.Username,
            Email = user.Email,
            FullName = $"{user.FirstName} {user.LastName}"
        };
    }
    
    // Domain Models
    public class User
    {
        public Guid Id { get; set; }
        public string Username { get; set; } = string.Empty;
        public string Email { get; set; } = string.Empty;
        public string FirstName { get; set; } = string.Empty;
        public string LastName { get; set; } = string.Empty;
        public DateTime CreatedAt { get; set; }
        public bool IsActive { get; set; }
        
        public ICollection<Post> Posts { get; set; } = new List<Post>();
    }
    
    public class Post
    {
        public Guid Id { get; set; }
        public string Title { get; set; } = string.Empty;
        public string Content { get; set; } = string.Empty;
        public Guid AuthorId { get; set; }
        public User Author { get; set; } = null!;
        public DateTime PublishedAt { get; set; }
    }
    
    // DTOs and Records
    public record UserDto
    {
        public Guid Id { get; init; }
        public string Username { get; init; } = string.Empty;
        public string Email { get; init; } = string.Empty;
        public string FullName { get; init; } = string.Empty;
    }
    
    public record CreateUserRequest(
        string Username,
        string Email,
        string FirstName,
        string LastName
    );
    
    // Interfaces
    public interface IUserService
    {
        Task<IEnumerable<UserDto>> GetUsersAsync(int page, int pageSize);
        Task<UserDto?> GetUserByIdAsync(Guid id);
        Task<UserDto> CreateUserAsync(CreateUserRequest request);
    }
    
    public interface IUserRepository
    {
        Task<IEnumerable<User>> GetAllAsync();
        Task<User?> GetByIdAsync(Guid id);
        Task AddAsync(User user);
    }
    
    public interface IEmailService
    {
        Task SendWelcomeEmailAsync(string email);
    }
    
    // Repository with EF Core
    public class UserRepository : IUserRepository
    {
        private readonly ApplicationDbContext _context;
        
        public UserRepository(ApplicationDbContext context)
        {
            _context = context;
        }
        
        public async Task<IEnumerable<User>> GetAllAsync()
        {
            return await _context.Users
                .Include(u => u.Posts)
                .ToListAsync();
        }
        
        public async Task<User?> GetByIdAsync(Guid id)
        {
            return await _context.Users
                .Include(u => u.Posts)
                .FirstOrDefaultAsync(u => u.Id == id);
        }
        
        public async Task AddAsync(User user)
        {
            await _context.Users.AddAsync(user);
            await _context.SaveChangesAsync();
        }
    }
    
    // Pattern Matching Examples
    public class PatternMatchingExamples
    {
        public string DescribeValue(object value) => value switch
        {
            null => "Null value",
            int i when i > 0 => $"Positive integer: {i}",
            int i when i < 0 => $"Negative integer: {i}",
            int => "Zero",
            string s when !string.IsNullOrEmpty(s) => $"String: {s}",
            string => "Empty string",
            User { IsActive: true } user => $"Active user: {user.Username}",
            User user => $"Inactive user: {user.Username}",
            _ => "Unknown type"
        };
        
        public bool IsValidEmail(string? email) => email switch
        {
            null or "" => false,
            _ when email.Contains("@") && email.Contains(".") => true,
            _ => false
        };
        
        public (bool isValid, string message) ValidateUser(User? user)
        {
            return user switch
            {
                null => (false, "User is null"),
                { IsActive: false } => (false, "User is not active"),
                { Email: null or "" } => (false, "Email is required"),
                _ => (true, "Valid user")
            };
        }
    }
    
    // Advanced LINQ Examples
    public class AdvancedLinqExamples
    {
        public async Task<Dictionary<string, int>> GetPostCountByAuthor(
            IEnumerable<Post> posts)
        {
            return posts
                .GroupBy(p => p.Author.Username)
                .ToDictionary(g => g.Key, g => g.Count());
        }
        
        public IEnumerable<UserPostSummary> GetUserPostSummaries(
            IEnumerable<User> users,
            IEnumerable<Post> posts)
        {
            return from user in users
                   join post in posts on user.Id equals post.AuthorId into userPosts
                   let postCount = userPosts.Count()
                   where postCount > 0
                   orderby postCount descending
                   select new UserPostSummary
                   {
                       Username = user.Username,
                       PostCount = postCount,
                       LatestPost = userPosts.OrderByDescending(p => p.PublishedAt).FirstOrDefault()
                   };
        }
        
        public async Task<IEnumerable<Post>> SearchPostsAsync(
            string searchTerm,
            DateTime? fromDate = null)
        {
            var query = _context.Posts.AsQueryable();
            
            if (!string.IsNullOrEmpty(searchTerm))
            {
                query = query.Where(p => 
                    p.Title.Contains(searchTerm) || 
                    p.Content.Contains(searchTerm));
            }
            
            if (fromDate.HasValue)
            {
                query = query.Where(p => p.PublishedAt >= fromDate.Value);
            }
            
            return await query
                .OrderByDescending(p => p.PublishedAt)
                .Take(50)
                .ToListAsync();
        }
    }
    
    public record UserPostSummary
    {
        public string Username { get; init; } = string.Empty;
        public int PostCount { get; init; }
        public Post? LatestPost { get; init; }
    }
    
    // Extension Methods
    public static class EnumerableExtensions
    {
        public static IEnumerable<T> WhereNotNull<T>(this IEnumerable<T?> source) 
            where T : class
        {
            return source.Where(item => item is not null)!;
        }
        
        public static async Task<List<TResult>> SelectManyAsync<TSource, TResult>(
            this IEnumerable<TSource> source,
            Func<TSource, Task<IEnumerable<TResult>>> selector)
        {
            var results = new List<TResult>();
            
            foreach (var item in source)
            {
                var itemResults = await selector(item);
                results.AddRange(itemResults);
            }
            
            return results;
        }
    }
}
